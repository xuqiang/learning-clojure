; -------------------metadata--------------------
; metadata类似于给data打标签
; meatadata不影响两个object的比较

; with-meta meta数据
(def untrusted (with-meta {:command "clean-table" :subject "users" }
	{ :safe false  :io true }))
; 查看meta数据
(meta untrusted)
; 如果新的object是从包含metadata的object中生成，metadata会被继承
(def still-suspect (assoc untrusted :complete?  false))
(meta still-suspect)

; 函数 macros 同样可以定义metadata
(defn 
	testing-meta "test metadata for functions"
	{:safe true :console true}
	[]
	(println "hello from meta"))

结果如下:
	
	user=> (meta testing-meta)
	nil

; 查看的函数的meta数据
(meta (var testing-meta))

