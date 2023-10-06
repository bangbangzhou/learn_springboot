git clone -b 2.0.3 https://github.com/nacos-group/nacos-docker.git

# 安装nacos
docker-compose -f nacos-docker/example/standalone-mysql-8.yaml up

