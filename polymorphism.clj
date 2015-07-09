; polymorphism 多态

; 两种多态:subtype polymorphism / duck typing 大概的含义是通过接口的方式来实现

; 函数重载
; defmulti 
; (defmulti name dispatch-fn & options)

; defmethod 重载函数具体实现 multifn 和 defmulti宏中的name相对应 
; 根据dispatch-value来决定具体调用那个函数 
; (defmethod multifn dispatch-value & options)
(defn fee-amount [percentage user]
	(float (* 0.01 percentage (:salary user))))
; referrer 作为判断条件 来决定具体调用那个函数
(defmulti affiliate-feed :referrer)
(defmethod affiliate-feed :mint.com [user]  
	(fee-amount 0.03 user))
(defmethod affiliate-feed :google.com [user]
	(fee-amount 0.01 user))
(defmethod affiliate-feed :default [user]
	(fee-amount 0.02 user))
(def user-1 { :login "rob" :referrer :mint.com :salary 10000 })
(def user-2 { :login "kyle" :referrer :google.com :salary 10000 })
(def user-3 { :login "celeste" :referrer :yahoo.com :salary 10000 })
(affiliate-feed user-1)
(affiliate-feed user-2)
(affiliate-feed user-3)

; 如果dispatch-fn 需要多个参数
(defn fee-category [user]
	[ (:referrer user) (:profit-rating user) ])
; 定义格式(defmulti name dispathc-fn)
(defmulti profit-based-affiliate-feed fee-category)
(defmethod profit-based-affiliate-feed [:mint.com ::bronze] [user]
	(fee-amount 0.03 user))
(defmethod profit-based-affiliate-feed [:google.com ::gold] [user]
	(fee-amount 0.01 user))

; 类型继承 bronze 继承自 basic
(derive ::bronze ::basic)
(derive ::silver ::basic)
