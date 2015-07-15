; concurrent
; 通常的并发编程中，使用使用 锁 结构来保证数据的完整性。clojure提供另外一种方式。
; clojure保存各个数据结构的多个版本。提供了四个关键字
; ref agent atom var


; ref (reference简写) 同步的 数据更新之后 能够同步收到
(def all-users (ref {}))
; 读取ref的数据
(deref all-users)
; 或者使用宏@来deref 如果是在(dosync) 中读取这个数据，保证读到数据一致性
@all-users
; ref-set 修改ref的数据 ref-set必须在一个STM transaction 使用dosync宏
(dosync 
	(ref-set all-users {}))
; 一个通常用的情景 读取数据deref 处理 调用ref-set设定新值 
; 为此clojure提供了另外的宏 alter
; (alter ref function & args)
(defn new-user [id login monthly-budget]
	{:id id
		:login login
		:monthly-budget monthly-budget
		:total-expenses 0})
(defn add-new-user [login budget-amount]
	(dosync 
		(let [ current-number (count @all-users)
		user (new-user (inc current-number) login budget-amount) ]
		(alter all-users assoc login user))))
; commute (commutative交换) 一个函数可交换，标识函数的参数的传入顺序 对函数最终的返回
; 结果没有影响 例如+等
; (commute ref funciton & args)  这里的function是需要可交换的

; STM software transactional memory 
; stm的介绍 <http://java.ociweb.com/mark/stm/article.html#Overview>

; 并发编程模型 lock-based actor-based(相互之间通过消息传递 每个actor运行在一个process 没有内存的共享)
; transactional memory 提供acid特性
; A atomic 事务要不全部提交 要不都不提交
; C consitent 一致性 在一个事务内，看到的数据结构的内容是相同的 
; Transactions operate on a consistent snapshot of the memory
; I isolated 在事务内部 修改的数据结构 在其他的线程 是不可见的
; D durable 如果一个事务被commit了，所有的更改是不可能丢失的

; 事务模型 可能存在重试的情况，例如两个thread并发执行，如果thread a修改了
; 变量 在thread b之前提交，b会commit失败，重新执行

; clojure 内部使用mmvc方式实现的  

; clojure使用agent agent实现异步 / 独立的修改
; 创建agent
(def total-cpu-time (agent 0))
; 得到值
(deref total-cpu-time)
; 或者使用@
@total-cpu-time
; (send the-agent the-funciton & args) 如果 the-function 执行的时间比较长
; deref可能需要等一段时间 才能去到更新之后的数值
(send total-cpu-time + 700)









