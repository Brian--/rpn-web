(ns brian.util)

(defn validate
  ([pred x]
  (validate pred x nil))

  ([pred x default]
  (if (pred x) x default)))

(defn number
  [x]
  (validate number? x))

(defn nofn
  ([x] nil)
  ([x &xs] nil))

(defn trimclo
  [c s]
  (str (apply str (drop-while #{c} (butlast s))) (last s)))
