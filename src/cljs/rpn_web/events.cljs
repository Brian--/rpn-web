(ns rpn-web.events
  (:require
   [re-frame.core :as re-frame]
   [rpn-web.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [cljs.reader :refer [read-string]]
   [brian.util :refer [number trimclo]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(defn-traced enter
  [coeffects _]
  (-> coeffects
      (update-in [:db :num-stack] #(if-let [num (as-> coeffects <>
                                                    (get-in <> [:db :input])
                                                    (read-string <>)
                                                    (number <>))]
                                     (conj % num)
                                     %))
      (assoc-in [:db :input] "")))
(re-frame/reg-event-fx
 :enter enter)

(defn-traced display
  [coeffects [_ val]]
  (update-in coeffects [:db :input] str val))
(re-frame/reg-event-fx
 :display display)

(defn-traced operate
  [coeffects [_ op arity]]
  (update-in coeffects [:db :num-stack]
             #(apply conj ((juxt
                            drop
                            (comp (partial apply op) reverse take))
                           arity %))))
(re-frame/reg-event-fx
 :operate (fn-traced [coeffects event]
                     (-> coeffects
                         (enter event)
                         (operate event))))

