# master
mkdir -p /redis-master-slave-sentinel/redis/master/config /redis-master-slave-sentinel/redis/master/data
cd /redis-master-slave-sentinel/redis/master/config
curl -O -L http://download.redis.io/releases/redis-6.0.20.tar.gz
tar -zxvf redis-6.0.20.tar.gz
cp /redis-master-slave-sentinel/redis/master/config/redis-6.0.20/redis.conf  /redis-master-slave-sentinel/redis/master/config/redis.conf
sed -i 's/bind 127.0.0.1/bind 0.0.0.0/' /redis-master-slave-sentinel/redis/master/config/redis.conf
# slave-1
mkdir -p /redis-master-slave-sentinel/redis/slave-1/config /redis-master-slave-sentinel/redis/slave-1/data
cd /redis-master-slave-sentinel/redis/slave-1/config/
cp /redis-master-slave-sentinel/redis/master/config/redis-6.0.20/redis.conf   /redis-master-slave-sentinel/redis/slave-1/config/redis.conf
sed -i 's/bind 127.0.0.1/bind 0.0.0.0/' /redis-master-slave-sentinel/redis/slave-1/config/redis.conf

# slave-2
mkdir -p /redis-master-slave-sentinel/redis/slave-2/config /redis-master-slave-sentinel/redis/slave-2/data
cd /redis-master-slave-sentinel/redis/slave-2/config/
cp /redis-master-slave-sentinel/redis/master/config/redis-6.0.20/redis.conf   /redis-master-slave-sentinel/redis/slave-2/config/redis.conf
sed -i 's/bind 127.0.0.1/bind 0.0.0.0/' /redis-master-slave-sentinel/redis/slave-2/config/redis.conf


#redis-sentinel-1
mkdir -p /redis-master-slave-sentinel/sentinel/redis-sentinel-1  /redis-master-slave-sentinel/sentinel/redis-sentinel-1/s1
cd  /redis-master-slave-sentinel/sentinel/redis-sentinel-1


cat >> /redis-master-slave-sentinel/sentinel/redis-sentinel-1/sentinel.conf <<EOF
# 配置可参考 http://download.redis.io/redis-stable/sentinel.conf
# 配置说明 https://redis.io/topics/sentinel
port 26379
dir /tmp
sentinel monitor mymaster 192.168.150.102 6380 2
sentinel down-after-milliseconds mymaster 30000
sentinel parallel-syncs mymaster 1
sentinel auth-pass mymaster 123456
sentinel failover-timeout mymaster 180000
sentinel deny-scripts-reconfig yes
EOF



#redis-sentinel-2
mkdir -p /redis-master-slave-sentinel/sentinel/redis-sentinel-2  /redis-master-slave-sentinel/sentinel/redis-sentinel-2/s2
cd  /redis-master-slave-sentinel/sentinel/redis-sentinel-2


cat >> /redis-master-slave-sentinel/sentinel/redis-sentinel-2/sentinel.conf <<EOF
# 配置可参考 http://download.redis.io/redis-stable/sentinel.conf
# 配置说明 https://redis.io/topics/sentinel
port 26379
dir /tmp
sentinel monitor mymaster 192.168.150.102 6380 2
sentinel down-after-milliseconds mymaster 30000
sentinel parallel-syncs mymaster 1
sentinel auth-pass mymaster 123456
sentinel failover-timeout mymaster 180000
sentinel deny-scripts-reconfig yes
EOF


#redis-sentinel-3
mkdir -p /redis-master-slave-sentinel/sentinel/redis-sentinel-3  /redis-master-slave-sentinel/sentinel/redis-sentinel-3/s3
cd  /redis-master-slave-sentinel/sentinel/redis-sentinel-3


cat >> /redis-master-slave-sentinel/sentinel/redis-sentinel-3/sentinel.conf <<EOF
# 配置可参考 http://download.redis.io/redis-stable/sentinel.conf
# 配置说明 https://redis.io/topics/sentinel
port 26379
dir /tmp
sentinel monitor mymaster 192.168.150.102 6380 2
sentinel down-after-milliseconds mymaster 30000
sentinel parallel-syncs mymaster 1
sentinel auth-pass mymaster 123456
sentinel failover-timeout mymaster 180000
sentinel deny-scripts-reconfig yes
EOF