(ns rpn-web.css
  (:require [garden.def :refer [defstyles]]
            [garden.color :as color :refer [rgb]]
            [garden.units :refer :all]))

(defstyles screen
  [:body   {:background-color (rgb 72 61 63)}]
  [:.num   {:background-color (rgb 0 144 234)}]
  [:.op    {:background-color (rgb 163 154 146)}]
  [:.enter {:background-color (rgb 119 104 93)}]

  [:button.calc {:border "none"
                 :border-radius (px 0)
                 :font-size (px 22)
                 :font-weight 300
                 :width (percent 99)
                 :height (percent 99)
                 }]

  [:.display {:width (em 36)}]
  [:#numpad {:display "grid"
             :grid    "repeat(5, 10em) / auto-flow 25%"}]
  [:#calc {:width  (em (* 4 9))
           :height (em (* 4 15))}]
  )
