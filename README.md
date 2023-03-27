# rpc-framework
RPC framework based on Java language implementation

## 介绍
- [ ] **使用 Netty（基于 NIO）替代 BIO 实现网络传输；**
- [x] **使用开源的序列化机制 Kyro 替代 JDK 自带的序列化机制；**
- [x] **使用 Zookeeper 管理相关服务地址信息**
- [x] Netty 重用 Channel 避免重复连接服务端
- [x] **借助 Spring 通过注解注册服务**
- [x] **在客户端实现了基于一致性哈希算法的负载均衡**
- [x] **使用JDK动态代理屏蔽远程方法调用的底层细节**
