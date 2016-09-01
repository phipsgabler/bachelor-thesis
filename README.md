# Designing Embedded Domain-Specific Languages in Scala #

This repository contains the LaTeX source of my bachelor thesis at TU Graz, 
in the submitted version, and perhaps including some corrections in the future.
This includes the accompanying presentation I gave about the topic, and some
TeX-y stuff I used: my packages for [typgraphical improvements](packages/typographic_setup.sty)
and [listings customizations](packages/mylistings.sty), as well as a small [fix](packages/neohellenic_fixed.sty)
to the Neohellenic font package, and a copy of an older `tikz-uml` version, which stopped 
working between updates.

There are two PDFs, one for [reading on screen](thesis/document.pdf), and one 
optimized for [printing](thesis/document-print.pdf) (which essentialy amounts 
to no color and two-sided layout).

## Abstract ##

Programming languages have been and still are becoming more and more
abstract, and increasingly specialized complicated applications and libraries
are implemented. With that tendency, a great amount of “linguistic” flexibility
is both available and needed. However, the combination of the power at hand
of the programmer and the need to describe the increasingly complex systems
has yet to be fully explored; this is the traditional habitat of Domain Specific
Languages (DSLs).

DSLs have been in long use in the programming community. The philosopy
behind them is the following: do not try to come up with a syntax so general
that it can concisely express every problem (that is probably impossible); rather,
define a smaller language to describe the specific problem, and embed that into a
system which can combine the specific languages. In the case of embedded DSLs,
that outer system is itself a general-purpose programming language, though
preferably one which properly supports embedding small sublanguages in a
convenient way.

Often, LISP has been termed such a “programmable programming language”.
As of today, Scala probably comes closest to this high claim, at least from the
perspective of a programmer used to conventional languages. This bachelor
thesis tries to explore how Scala makes it easy to write DSLs, which allow to
express specific problems such that there is no additional “code noise” around
them, and that the description really behaves in a transparent way, like a reader
would expect it from the code.

For that purpose, in the first part an overview is given of DSLs in general and
Scala’s special features that help with implementing them, complemented with
a description of some useful patterns for that purpose. Then, in the second part,
the implementation of a specific DSL for Action Systems is described. Action
Systems are a formalism originally used to formalize behaviour of distributed,
concurrent systems; in the context of this work, however, they are treated
more as in their interpretation as non-deterministic transition systems, which
is a useful point of view for performing model-based testing.

# License #

This work is licensed under a [Creative Commons Attribution-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-sa/4.0/) (see [LICENSE.md](LICENSE.md)).
