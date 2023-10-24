(ns scicloj.clay.v2.state)

(defonce *state
  (atom {:port nil
         :fns {}
         :counter 0
         :page nil}))

(defn counter []
  (:counter @*state))

(defn swap-state! [f & args]
  (-> *state
      (swap!
       (fn [state]
         (-> state
             (#(apply f % args)))))))

(defn swap-state-and-increment! [f & args]
  (swap-state!
   (fn [state]
     (-> state
         (update :counter inc)
         (#(apply f % args))))))

(defn set-page! [page]
  (swap-state-and-increment! assoc :page page))

(defn page []
  (:page @*state))

(defn reset-quarto-html-path! [path]
  (swap-state! assoc :quarto-html-path path))

(defn quarto-html-path []
  (:quarto-html-path @*state))

(defn set-port! [port]
  (swap-state! assoc :port port))

(defn port []
  (:port @*state))