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
   :class "numpad calc"])

(defn op-button
  [displ opfn arity]
  [re-com/button
   :label (str displ)
   :on-click #(re-frame/dispatch [:operate opfn arity])
   :class "operator calc"])

(defn enter-button
  []
  [re-com/button
   :label "⏎"
   :on-click #(re-frame/dispatch [:enter])
   :class "enter calc rounded-bottom rounded-right"])

(defn input-field
  []
  [re-com/input-text
   :model (re-frame/subscribe [:query-input])
   :on-change nofn
   :class "input display uneditable-input"])

(defn input-history
  []
  [re-com/input-textarea
   :rows 3
   :model (re-frame/subscribe [:query-stack])
   :on-change nofn
   :style {:resize   "vertical"
           :overflow "auto"}
  ])

(defn main-panel []
  [re-com/v-box :class "calc grid column":height "100%" :width "283"
   :children [[title]
              [input-history]
              [input-field]
              [re-com/h-box :class "calc grid row":justify :around
               :children [[num-button 7]   [num-button 8]   [num-button 9] [op-button "/" / 2]]]
              [re-com/h-box :class "calc grid row":justify :around
               :children [[num-button 4]   [num-button 5]   [num-button 6] [op-button "*" * 2]]]
              [re-com/h-box :class "calc grid row":justify :around
               :children [[num-button 1]   [num-button 2]   [num-button 3] [op-button "-" - 2]]]
              [re-com/h-box :class "calc grid row":justify :around
               :children [[op-button "-" - 1] [num-button 0]   [op-button "+" + 2] [enter-button]]]
              ]])
