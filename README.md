# rpn-web

A web-based RPN (Reverse Poish Notation) calculator written in clojurescript.

## Features
* Infinite stack size
* Basic arithmetic operations
* basic stack operations
* Derivatives of zero order polynomials (using the `clear` button).

## Building

### Development

```
lein dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Production


To compile clojurescript to javascript:

```
lein build
```

The finished app is built into `resources/public`
