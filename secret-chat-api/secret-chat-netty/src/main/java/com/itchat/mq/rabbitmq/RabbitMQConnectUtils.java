package com.itchat.mq.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RabbitMQConnectUtils {

    private final List<Connection> connections = new ArrayList<>();
    private final int maxConnection = 20;

    // 开发环境 dev
    private final String host = "127.0.0.1";
    private final int port = 5672;
    private final String username = "root";
    private final String password = "12345678";
    private final String virtualHost = "imchat";

    // 生产环境 prod
    //private final String host = "";
    //private final int port = 5672;
    //private final String username = "123";
    //private final String password = "123";
    //private final String virtualHost = "123";

    public ConnectionFactory factory;

    public ConnectionFactory getRabbitMqConnection() {
        return getFactory();
    }

    public ConnectionFactory getFactory() {
        initFactory();
        return factory;
    }

    private void initFactory() {
        try {
            if (factory == null) {
                factory = new ConnectionFactory();
                factory.setHost(host);
                factory.setPort(port);
                factory.setUsername(username);
                factory.setPassword(password);
                factory.setVirtualHost(virtualHost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message, String queue) throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("",
                queue,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes("utf-8"));
        channel.close();
        setConnection(connection);
    }

    public void sendMsg(String message, String exchange, String routingKey) throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish(exchange,
                routingKey,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes("utf-8"));
        channel.close();
        setConnection(connection);
    }

    public GetResponse basicGet(String queue, boolean autoAck) throws Exception {
        GetResponse getResponse = null;
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        getResponse = channel.basicGet(queue, autoAck);
        channel.close();
        setConnection(connection);
        return getResponse;
    }

    public Connection getConnection() throws Exception {
        return getAndSetConnection(true, null);
    }

    public void setConnection(Connection connection) throws Exception {
        getAndSetConnection(false, connection);
    }

    private synchronized Connection getAndSetConnection(boolean isGet, Connection connection) throws Exception {
        getRabbitMqConnection();

        if (isGet) {
            if (connections.isEmpty()) {
                return factory.newConnection();
            }
            Connection newConnection = connections.get(0);
            connections.remove(0);
            if (newConnection.isOpen()) {
                return newConnection;
            } else {
                return factory.newConnection();
            }
        } else {
            if (connections.size() < maxConnection) {
                connections.add(connection);
            }
            return null;
        }
    }

    // 监听
    public void listener(String exchangeName, String queueName) {
        try {
            Connection connection = getConnection();
            Channel channel = connection.createChannel();

            // 定义交换机
            // 发布订阅模式 + 持久化 + 不自动删除 + 没有参数
            channel.exchangeDeclare(exchangeName,
                    BuiltinExchangeType.FANOUT,
                    true,
                    false,
                    null);

            // 定义队列
            // 持久化 + + 不自动删除 + 没有参数
            channel.queueDeclare(queueName, true, false, false, null);

            // 创建绑定关系
            channel.queueBind(queueName, exchangeName, "");

            // 创建消费者
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                /**
                 * 重写消息配送方法
                 * @param consumerTag 消息的标签
                 * @param envelope 一些消息(交换机，路由等信息)
                 * @param properties 相关的配置信息和内容
                 * @param body 收到的消息数据
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String exchange = envelope.getExchange();
                    if (exchangeName.equalsIgnoreCase(exchange)) {
                        String msg = new String(body);

                        System.out.println("msg = " + msg);
                    }
                }
            };

            // 需要监听的队列名称 + 是否自动确认 + 回调（处理监听到的数据）
            channel.basicConsume(queueName,true,defaultConsumer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
