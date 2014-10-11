(ns example.client
  (:require-macros [cljs.core.async.macros :refer (go)])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [<! chan put!]]
            [cljs-http.client :as http]
            [figwheel.client :as fw]
            [example.child :refer (child)]))

(enable-console-print!)

(defonce app-state (atom {:text ""}))

(defn main []
  (om/root
   (fn [app owner]
     (reify
       om/IRender
       (render [_]
         (dom/div
          nil
          (om/build child app)))
       om/IWillUnmount
       (will-unmount [_]
         (println "I will unmount"))))
   app-state
   {:target (. js/document (getElementById "app"))}))

(fw/watch-and-reload
 :websocket-url "ws://localhost:3449/figwheel-ws"
 :jsload-callback
 (fn []
   (println "reloaded")
   (main)))

(defonce initial-call-to-main (main))
