package com.carrotsearch.hppc;

import static com.carrotsearch.hppc.Containers.*;
import static com.carrotsearch.hppc.HashContainers.*;

/**
 * Same as {@link KTypeOpenHashSet} but does not implement per-instance
 * key mixing strategy and uses a simpler (faster) bit distribution function.
 * 
 * Scatter sets are useful for containment checks or counting but should not be 
 * used when keys are copied from one hash container to another (because the 
 * keys of a scatter set are nearly-sorted by their hash value and can cause conflict 
 * avalanching leading to exponential times for any slot-lookup operation).
 */
/*! ${TemplateOptions.generatedAnnotation} !*/
public class KTypeScatterSet<KType> extends KTypeOpenHashSet<KType> {
  /**
   * New instance with sane defaults.
   */
  public KTypeScatterSet() {
    this(DEFAULT_EXPECTED_ELEMENTS, DEFAULT_LOAD_FACTOR);
  }

  /**
   * New instance with sane defaults.
   */
  public KTypeScatterSet(int expectedElements) {
    this(expectedElements, DEFAULT_LOAD_FACTOR);
  }

  /**
   * New instance with sane defaults.
   */
  @SuppressWarnings("deprecation")
  public KTypeScatterSet(int expectedElements, double loadFactor) {
    super(expectedElements, loadFactor, HashOrderMixing.none());
  }

  /*! #if ($templateonly) !*/
  @Override
  public
  /*! #else protected #end !*/
  int hashKey(KType key) {
    return BitMixer.phiMix(key);
  }
}