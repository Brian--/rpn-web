(ns rpn-web.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :query-stack
 (fn [db]
   (->> db
       (:num-stack)
       (reverse)
       (interleave (repeat \newline))
       (apply str))))

(re-frame/reg-sub
 :query-input
 (fn [db]
   (:input db)))

