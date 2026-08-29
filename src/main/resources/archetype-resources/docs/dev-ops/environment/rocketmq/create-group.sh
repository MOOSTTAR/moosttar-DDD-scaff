# 命令创建 消费者组 Group moosttar-group 你可以更换你需要的
docker exec -it rocketmq sh /home/app/rocketmq/bin/mqadmin updateSubGroup -n localhost:9876 -c DefaultCluster -g moosttar-group
