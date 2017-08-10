======================
The Simple Transformer
======================

The Simple Transformer (*SiTra*) is a simple Java model-to-model transformation (M2M) framework.
Its aim is to provide users the ability to use M2M using a general purpose language and to provide primitives to create their own engine and rules.
At its core are two interfaces:

.. code-block:: java

  public interface Rule<S, T> { }

.. code-block:: java

  public interface Transformer { }
