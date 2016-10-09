(ns number-visual.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/number-visual/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Lets look at numbers!"}))

(defn number-visual []
  [:div
   [:h1 (:text @app-state)]
   [:svg
    [:circle {:r 10 :cx 10 :cy 10}]]])

(reagent/render-component [number-visual]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
