# shows available recipies
help:
	just --list

nrepl:
	clojure -M:dev -m nrepl.cmdline

nrepl-docker:
	clojure -M:dev -m nrepl.cmdline -b 0.0.0.0 -p 7777

format_check:
    clojure -M:format -m cljfmt.main check src dev test

format:
    clojure -M:format -m cljfmt.main fix src dev test

lint:
    clojure -M:lint -m clj-kondo.main --lint .

outdated:
    clojure -Sdeps '{:deps {com.github.liquidz/antq {:mvn/version "RELEASE"}}}' -M -m antq.core
