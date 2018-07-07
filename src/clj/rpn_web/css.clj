(ns rpn-web.css
  (:require [garden.def :refer [defstyles]]
            [garden.color :as color :refer [rgb]]
            [garden.units :refer :all]))

(defstyles screen
  [:body      {:background-color (rgb 72 61 63)}]
  [:.numpad   {:background-color (rgb 0 144 234)}]
  [:.operator {:background-color (rgb 163 154 146)}]
  [:.enter    {:background-color (rgb 119 104 93)}]

  [:button.calc {:border "none"
                 :border-radius (px 0)
                 :font-size (px 22)
                 :font-weight 300
                 :width (px 65)
                 :height (percent 98)
                 }]

  [:.grid {:width (percent 100)
         }]
  [:.row {:height (px 91)}]

  [:.display {:width (percent 100)}]
  )
