(ns kafka.core
  (:require [clj-kafka.producer :as p]
            [clj-kafka.zk :as zk]
            [clj-kafka.consumer.zk :as zk-c]))



;;  List Kafka brokers
(zk/brokers {"zookeeper.connect" "192.168.33.21:2181"})

;; Note that you need the broker in your hosts file
(def producer-config (p/producer
                       {"metadata.broker.list" "192.168.33.21:9092"
                        "serializer.class"            "kafka.serializer.StringEncoder"
                        "request.required.acks"       "1"}))

(p/send-message producer-config (p/message "kafkatopic" "from clj!"))


(def consumer-config
  {"zookeeper.connect" "192.168.33.21:2181"
   "group.id" "mygroup"
   "auto.offset.reset" "smallest"
   "auto.commit.enable" "false"})



;; Consumer
;;

(defn consumit [topic]
  (clj-kafka.core/with-resource
    [c (zk-c/consumer consumer-config)]
    zk-c/shutdown
    (doall (zk-c/messages c topic))))


;; Produce messages
(prn "Producing messages")
(p/send-message producer-config (p/message "kafkatopic" "Some message"))
(p/send-message producer-config (p/message "kafkatopic" "Other message"))

;; Read messages
(prn "Consuming messages")
(prn (consumit "kafkatopic"))
(prn "Done!")