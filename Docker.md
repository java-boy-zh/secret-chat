### **容器化安装Mysql**

```shell
docker pull mysql:8.3.0
```

```shell
mkdir /Volumes/wang/docker/mysql8.3.0/log -p
mkdir /Volumes/wang/docker/mysql8.3.0/data -p
mkdir /Volumes/wang/docker/mysql8.3.0/conf -p
mkdir /Volumes/wang/docker/mysql8.3.0/mysql-files -p
```

```sh
docker run -p 3306:3306 --name mysql8 \
-v /Volumes/wang/docker/mysql8.3.0/log:/var/log/mysql \
-v /Volumes/wang/docker/mysql8.3.0/data:/var/lib/mysql \
-v /Volumes/wang/docker/mysql8.3.0/conf:/etc/mysql/conf.d \
-v /Volumes/wang/docker/mysql8.3.0/mysql-files:/var/lib/mysql-files \
--restart always
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:8.3.0 \
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

### **容器化安装Redis**

```shell
docker pull redis:7.2.4
```

```shell
mkdir /Volumes/wang/docker/redis/conf -p
mkdir /Volumes/wang/docker/redis/data -p
touch redis.conf
```

```sh
requirepass wangzh
appendonly yes
```



```shell
docker run -p 6379:6379 --name redis \
-v /Volumes/wang/docker/redis/data:/data \
-v /Volumes/wang/docker/redis/conf/redis.conf:/etc/redis/redis.conf \
--restart always \
-d redis:7.2.4 \
redis-server /etc/redis/redis.conf
```

### **容器化安装Nacos**

```sh
docker pull nacos/nacos-server:v2.3.1
```

```sh
docker run --name nacos \
-e MODE=standalone \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
-p 7848:7848 \
--restart always \
--privileged=true \
-e PREFER_HOST_MODE=hostname \
-d nacos/nacos-server:v2.3.1
```

```sh
docker pull nacos/nacos-server:v2.2.1

docker run -d \
--name nacos \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
-p 7848:7848 \
--privileged=true \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e NACOS_AUTH_CACHE_ENABLE=enable \
-e NACOS_AUTH_TOKEN=SecretKey012345678901234567890123456789012345678901234567890123456789 \
-e NACOS_AUTH_IDENTITY_KEY=nacos \
-e NACOS_AUTH_IDENTITY_VALUE=nacos \
-e PREFER_HOST_MODE=hostname \
--restart=always \
nacos/nacos-server:v2.2.1
```



```sh
sudo mkdir -p /home/docker/nacos/logs
sudo mkdir -p /home/docker/nacos/data
sudo mkdir -p /home/docker/nacos/conf
sudo touch /home/docker/nacos/conf/application.properties
```

```sh
docker run \
--name nacos -d \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
-p 7848:7848 \
--privileged=true \
--restart=always \
-e JVM_XMS=512m \
-e JVM_XMX=1024m \
-e MODE=standalone \
--restart always \
-e PREFER_HOST_MODE=hostname \
-v /home/docker/nacos/logs:/home/nacos/logs \
-v /home/docker/nacos/data:/home/nacos/data \
-v /home/docker/nacos/conf/application.properties:/home/nacos/conf/application.properties \
nacos/nacos-server:v2.3.0
```

```properties
nacos.core.auth.enabled=true
#权限缓存开关，开启后权限缓存的更新默认有15秒的延迟，默认 : false
nacos.core.auth.caching.enabled=true
nacos.core.auth.server.identity.key=${NACOS_AUTH_IDENTITY_KEY:example}
nacos.core.auth.server.identity.value=${NACOS_AUTH_IDENTITY_KEY:example}
#自定义用于生成JWT令牌的密钥
#注意：原始密钥长度不得低于32字符，且一定要进行Base64编码，否则无法启动节点
server.tomcat.accesslog.enabled=true
nacos.core.auth.plugin.nacos.token.secret.key=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdef=

#告诉 Nacos 使用主机名作为服务的优选模式
#server.address=ip地址
#spring.datasource.platform=mysql
#db.num=1
#db.url.0=jdbc:mysql://ip地址:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
#db.user=用户名
#db.password=密码
```

### **容器化安装Minio**

```sh
docker pull bitnami/minio:2024.8.29
```

#### 配置

1. 创建/usr/local/minio/data文件夹

2. 授予权限

3. ```sh
   chmod -R 777 /usr/local/minio/data
   ```

#### 启动Minio容器

```sh
docker run -it -d --name minio \
-p 9000:9000 -p 9001:9001 \
-v /usr/local/minio/data:/data \
-e TZ=Asia/Shanghai --privileged=true \
--env MINIO_ROOT_USER="root" \
--env MINIO_ROOT_PASSWORD="12345678" \
bitnami/minio:2024.8.29
```

```sh
E:/IMchat/Docker/minio/data:/data

docker run -it -d --name minio -p 9000:9000 -p 9001:9001 -v E:/IMchat/Docker/minio/data:/data -e TZ=Asia/Shanghai --privileged=true --env MINIO_ROOT_USER="root" --env MINIO_ROOT_PASSWORD="12345678" bitnami/minio:2024.8.29
```

### **容器化安装RabbitMQ**

```she
docker pull rabbitmq:3.13-management
```

```she
docker run --name rabbitmq \
-p 5671:5671 \
-p 5672:5672 \
-p 4369:4369 \
-p 15671:15671 \
-p 15672:15672 \
-p 25672:25672 \
--restart always \
-d rabbitmq:3.13-management
```

```http
http://localhost:15672/
默认用户名密码为：guest guest
```

