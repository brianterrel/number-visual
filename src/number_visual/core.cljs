(ns number-visual.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

;(defn make-points
;  "selects evenly spaced points around a circle"
;  [number size offset]
;  (for [i (map inc (range number))]
;        [:circle {:r size
;                  :cy (- 50 (* offset (Math/cos (/ (* i (* 2 Math/PI)) number))))
;                  :cx (+ 50 (* offset (Math/sin (/ (* i (* 2 Math/PI)) number))))}]))

(defn make-points
  "selects evenly spaced points around a circles from a vector of center points"
  [centers number offset-radius]
  (loop [point (first centers)
         loop-seq (rest centers)
         acc ()]
    (if (empty? loop-seq)
      (concat acc (for [i (map inc (range number))]
                        {:y (- (:y point) (* offset-radius (Math/cos (/ (* i (* 2 Math/PI)) number))))
                         :x (+ (:x point) (* offset-radius (Math/sin (/ (* i (* 2 Math/PI)) number))))}))
      (recur (first loop-seq)
             (rest loop-seq)
             (concat acc (for [i (map inc (range number))]
                        {:y (- (:y point) (* offset-radius (Math/cos (/ (* i (* 2 Math/PI)) number))))
                         :x (+ (:x point) (* offset-radius (Math/sin (/ (* i (* 2 Math/PI)) number))))}))))))

(make-points [{:x 50 :y 50}] 3 30)


(defn draw-circles
  "returns a list of circle elements from a vector of center points"
  [centers radius]
  (loop [point (first centers)
         loop-seq (rest centers)
         acc ()]
    (if (empty? loop-seq)
      (concat acc (vector [:circle {:r radius
                                  :cx (:x point)
                                  :cy (:y point)}]))
      (recur (first loop-seq)
             (rest loop-seq)
             (concat acc (vector [:circle {:r radius
                                  :cx (:x point)
                                  :cy (:y point)}]))))))

(defonce app-state (atom {:text "Let's look at a number!"
                          :number 2}))

(defn number-visual []
  [:center
   [:h1 (:text @app-state)]
   [:svg
    {:view-box "0 0 100 100"
     :width 500
     :height 500}
    (draw-circles (make-points [{:x 50 :y 50}] 5 30) 10)]])

(reagent/render-component [number-visual]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

