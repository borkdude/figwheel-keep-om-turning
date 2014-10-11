(ns example.child
  (:require-macros [cljs.core.async.macros :refer (go)])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn child [cursor owner]
  (om/component
   (dom/div nil
            (dom/p nil "I'm a child!")
            (dom/p nil (str "My cursor:"
                            (pr-str cursor)))
            (dom/button #js {:onClick
                             #(om/transact!
                               cursor
                               :clicks inc)}
                        "Click me!"))))
