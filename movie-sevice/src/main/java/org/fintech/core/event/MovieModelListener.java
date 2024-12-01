package org.fintech.core.event;

import lombok.RequiredArgsConstructor;
import org.fintech.core.service.SequenceGenerator;
import org.fintech.store.entity.SequenceAware;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieModelListener extends AbstractMongoEventListener<SequenceAware> {

    private final SequenceGenerator sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<SequenceAware> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(event.getSource().getSequenceName()));
        }
    }

}
