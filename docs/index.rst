======================
The Simple Transformer
======================

The Simple Transformer (*SiTra*) is a simple Java model-to-model transformation (M2M) framework.
Its aim is to provide users the ability to use M2M using a general purpose language and to provide primitives to create their own engine and rules.
At its core are three interfaces for defining a transformation rule and the engine required to run them, the scheduler.

^^^^^^^^
The Rule
^^^^^^^^

The rule defines the transformation of a source into a target and has three methods: a guard, an initialiser and a binder.

.. code-block:: java

  public interface Rule<S, T> {
    // the guard - is this rule applicable?
    public boolean check(S source);
    // the initialiser - if it is we need to build the target
    public T build(S source, Transformer tx);
    // the binder - we need to set the attributes on the target
    public void setProperties(T target, S source, Transformer tx);
  }

1. The guard returns a boolean to show whether the rule is applicable to the source it has been given.
2. The initialiser builds the target objects when a match is found.
3. The binder sets the properties within the target objects in respect to the source.

Warning
  Do not transform any objects within the ``build(S, Transformer)`` method!

  A transformer will return the result of ``build(S, Transformer)`` on a second invocation of the rule upon the same source, if it doesn't return then it cannot do that.
  Use ``setProperties(T, S, Transformer)`` to set attributes and transform other related objects.

^^^^^^^^^^^^^^^
The Transformer
^^^^^^^^^^^^^^^

The transformer provides the scheduling of the transformation engine.
How the rules are to be applied to the source objects to generate the resultant model.
It retains a lists of transformation rules and will use the first applicable rule to the source given, if not specified.

.. code-block:: java

  public interface Transformer {
    // transform something
    public <S, T> T transform(S source);
    // transform something with a specific rule
    public <S, T> T transform(S source, Class<? extends Rule<S, T>> with);
    // the list of available rules to this engine
    public Set<Class<? extends Rule<?, ?>>> getTransformationRules();
    // the internals and the settings of the transformer instance
    public Context getContext();
  }

^^^^^^^^^^^^^^^^^^^^^^^^^
The Transformer's Context
^^^^^^^^^^^^^^^^^^^^^^^^^

We introduced a context to the transformer to provide a separation of transformation logic and its internal information, as well as to allow for some storage during a transformation.
For example, we transformed a particular source meta-model into SVG, this required each rule to generate target elements using an ``SVGDocument`` as a factory.
This factory is not accessible to the transformation instance unless globally available, which brings its own issues (parralellisation, etc.).
When the root of this transformation was transformed into an ``SVGDocument`` this was then stored within the context, for use by other rules using a known key.

.. code-block:: java

  public interface Context {
    public Map<Object, Object> getConfiguration();
    // basically (r, s) -> t
    public Map<Entry<?, Class<? extends Rule<?, ?>>> ? extends Object> getCache();
  }

This particular context holds the transformation cache.
The cache holds the links between a source and the generated target via the rule that was applied.
