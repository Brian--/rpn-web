(ns rpn-web.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [rpn-web.subs :as subs]
   [brian.util :refer [nofn]]))

(defn title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :label (str @name)
     :level :level1]))

(defn num-button
  [val]
  [re-com/button
   :label (str val)
   :on-click #(re-frame/dispatch [:display val])
   :class "num calc"])

(defn op-button
  [displ & opfn]
   [re-com/button
    :label (str displ)
    :on-click #(re-frame/dispatch (into [:operate] opfn))
    :class "op calc"])

(defn enter-button
  []
  [re-com/button
   :label "⏎"
   :on-click #(re-frame/dispatch [:enter])
   :class "enter calc"])

(defn input-field
  []
  [re-com/input-text
   :disabled? true
   :model (re-frame/subscribe [:query-input])
   :on-change nofn
   :class "display"])

(defn input-history
  []
  [re-com/input-textarea
   :rows 3
   :disabled? true
   :model (re-frame/subscribe [:query-stack])
   :on-change nofn
   :class "display"
   :style {:resize   "vertical"
           :overflow "auto"}])

(defn main-panel []
  [:div {:id "calc"}
   [input-history]
   [input-field]
   [:div {:id "numpad"}
    [op-button "clear" (constantly 0)] ;TODO [empty] after `swap` fix
    [num-button 7]
    [num-button 4]
    [num-button 1]
    [num-button "."]

    [op-button "drop" (fn [a _] a) 2] ;TODO [(constantly '()) 1] after `swap` fix
    [num-button 8]
    [num-button 5]
    [num-button 2]
    [num-button 0]

    [op-button "swap" (fn [a b] (seq [b a])) 2] ;FIXME `into` stack if ret of opfn is seq
    [num-button 9]
    [num-button 6]
    [num-button 3]
    [op-button "(-)" - 1]

    [op-button "+" + 2]
    [op-button "-" - 2]
    [op-button "*" * 2]
    [op-button "/" / 2]
    [enter-button]

    ]
   ])
