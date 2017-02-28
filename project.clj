(defproject coldnew/ipify "0.1.0"
  :description "Clojure/Script library for https://www.ipify.org: A Simple IP Address API."
  :author "Yen-Chin, Lee"
  :url "https://github.com/coldnew/ipify-clj"
  :license {:name "MIT License"
            :url "https://github.com/coldnew/ipify-clj/blob/master/LICENSE"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.473"]
                 [org.clojure/core.async "0.3.441"]
                 [clj-http "3.4.1"]
                 [cheshire "5.7.0"]
                 [cljsjs/nodejs-externs "1.0.4-1"]]

  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-doo "0.1.7"]]

  :source-paths ["src"]
  :test-paths   ["test"]

  :clean-targets ^{:protect false} [:target-path "resources/public/js/" "target/"]

  :doo {:build "test"
        :paths {:slimer "./node_modules/.bin/slimerjs"}
        :alias {:default [:slimer]
                :browsers [:chrome :firefox]
                :all [:browsers :headless]}}

  :jvm-opts ^:replace ["-Dclojure.compiler.direct-linking=true"]

  :cljsbuild
  {:builds {:dev                {:source-paths ["src"]
                                 :compiler     {:output-to     "resources/public/js/dev.js"
                                                :main          coldnew.core
                                                :optimizations :none}}
            :test               {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          'coldnew.runner
                                                :optimizations :simple}}
            :advanced           {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          "coldnew.runner"
                                                :optimizations :advanced}}
            :none-test          {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          coldnew.runner
                                                :source-map    true
                                                :optimizations :none}}
            :node-none          {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "target/testable.js"
                                                :main          coldnew.runner
                                                :optimizations :none
                                                :target        :nodejs}}
            :node-advanced      {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "target/testable.js"
                                                :main          coldnew.runner
                                                :optimizations :advanced
                                                :target        :nodejs}}
            }}

  :signing {:gpg-key "C079AE25"}
  :deploy-repositories [["releases" :clojars]])
