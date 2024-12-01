package org.fintech.store.entity;

public interface SequenceAware {
    String getSequenceName();
    Long getId();
    void setId(Long id);
}
