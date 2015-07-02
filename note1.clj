(def hello (fn [] "hello world"))

(defn hellou [name]
	(println "hello", name))

(+ 1 2)

; clojure char表示
(println \a)

; clojure string ""表示
(.split "hello-world" "-")

; ratio类型 (/ 4 9) 如果除法两个都是整形 clojure不会计算结果
(/ 4 9)
(/ 4.0 9)

; ISeq接口，需要实现 first cons reset方法
(first [1 2 3])
; cons 构建新的list 1追加到[2 3 4]
(cons 1 [2 3 4])

; list类型 (a b c) clojure默认会解析()中的元素 并认为第一个元素是function / macro 
; 否则需要使用quote
(list 1 2 3)

; 数组
(vector 1 2 3) 
(def the-vector [1 2 3])
; 获取vector元素 数组下标从0开始
(get the-vector 1)
; 修改数组元素 把index＝1的位置修改为value＝2 注意这里是返回新的vector
(assoc the-vector 1 2)
; 获取the-vector的元素
(the-vector 0)

; map类型 定义
(def the-map { :a 1, :b 2 })
; 或者使用hash-map函数生成
(hash-map :a 1, :b 2)
; 获取map key对应的value
(the-map :a)
; assoc 增加key
( def update-map (assoc the-map :d 1) )
; dissoc 删除key
(dissoc update-map :a)
; 嵌套的情况
(def users { :kyle {
	:date-joined "2009-01-01"
	:summary {
		:average {
			:monthly 10000
			:yearly 10000
		}}} })
; 更新user结构
(defn set-average-in [users-map, user, type, amount]
	(let [user-map (users-map user) 
		summary-map (:summary user-map)
		averages-map (:average summary-map)]
		(assoc user-map user
			(assoc user-map :summary
				(assoc summary-map :average
					(assoc averages-map type amount))))))
(set-average-in users :kyle :monthly 20000)


