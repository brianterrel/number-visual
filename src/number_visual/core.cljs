(ns number-visual.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defn make-points
  "selects evenly spaced points around a circle"
  [number]
  (for [i (map inc (range number))]
        [:circle {:r 10
                  :cy (- 50 (* 40 (Math/cos (/ (* i (* 2 Math/PI)) number))))
                  :cx (+ 50 (* 40 (Math/sin (/ (* i (* 2 Math/PI)) number))))}]))

(defonce app-state (atom {:text "Let's look at a number!"
                          :number 2}))

(defn number-visual []
  [:center
   [:h1 (:text @app-state)]
   [:svg
    {:view-box "0 0 100 100"
     :width 500
     :height 500}
    (make-points 7)]])

(reagent/render-component [number-visual]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

