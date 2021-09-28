package com.f_lin.future.spring.cloud.tcc.rm;

/**
 * 资源管理器。try-confirm-cancel 三大模块的数据存取，都需要基于RM的本地事务完成
 * <p>
 * 基于RM本地事务实现TCC事务框架时，一个TCC型服务的cancel业务要么执行，要么不执行，不需要考虑部分执行的情况
 *
 * 掌握每个RM本地事务的状态以及它们与Try/Confirm/Cancel业务方法之间的对应关系，以此为基础，TCC事务框架才能有效的构建TCC全局事务。
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/17 10:07
 **/
public interface ResourceManager {
}
