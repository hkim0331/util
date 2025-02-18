# hkim0331/util

Learn how to use remote clojure libraries.

forge utils in `scratch` (or the `develop` branch?) then copy them here.

## Dependency

```
io.github.hkim0331/util {:git/tag "v0.4.65" :git/sha "9c102f4"}
```

## Usage

Initialize:

```clojure
(require '[hkim0331.util.core :as u])

```

when you use util bench, add

```
criterium/criterium {:mvn/version "0.4.6"}
```

to your deps, then,

```clojure
(require '[hkim0331.util.core :as u]
         '[hkim0331.util.bench :as b])
```

Use:

```
(u/factor-integer 100)
; =>

(b/time+ (u/tarai-lazy 15 5 5))
;=>
```

## Start REPL

    % clojure -M:dev -m nrepl.cmdline
    or
    % just dev
    or dev container,
    % just dev-container



## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2025 Hkim

_EPLv1.0 is just the default for projects generated by `deps-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
