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

1. The guard returns a boolean to show whether the rule is applicable to the source it has been given.
2. The initialiser builds the target objects when a match is found.
3. The binder sets the properties within the target objects in respect to the source.

.. code-block:: java

  public interface Rule<S, T> {
    // the guard - is this rule applicable?
    public boolean check(S source);
    // the initialiser - if it is we need to build the target
    public T build(S source, Transformer tx);
    // the binder - we need to set the attributes on the target
    public void setProperties(T target, S source, Transformer tx);
  }

.. DANGER::

  This is important.

^^^^^^^^^^^^^^^
The Transformer
^^^^^^^^^^^^^^^

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

.. code-block:: java

  public interface Context {
    public Map<Object, Object> getConfiguration();
    // basically (r, s) -> t
    public Map<Entry<?, Class<? extends Rule<?, ?>>> ? extends Object> getCache();
  }
