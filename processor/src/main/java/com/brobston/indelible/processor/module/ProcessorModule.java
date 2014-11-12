package com.brobston.indelible.processor.module;

import com.brobston.indelible.processor.pre.CodeFileProcessor;
import com.brobston.indelible.processor.pre.CodeFileProcessorFactory;
import com.brobston.indelible.processor.pre.CodeMetadata;
import com.brobston.indelible.processor.pre.CodeMetadataFactory;
import com.brobston.indelible.processor.pre.antlr.*;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import org.antlr.v4.runtime.*;

/**
 * Created by andrew on 11/8/14.
 */
public class ProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(CodeFileProcessor.class, AntlrCodeFileProcessor.class)
                                          .build(CodeFileProcessorFactory.class));
        install(new FactoryModuleBuilder().implement(IOProvider.class, CharStreamProvider.class)
                                          .build(CharStreamProviderFactory.class));
        install(new FactoryModuleBuilder().implement(CodeMetadata.class, AntlrCodeMetadata.class)
                                          .build(CodeMetadataFactory.class));
        bind(TokenSource.class).to(Lexer.class);
        try {
            bind(Lexer.class).toConstructor(Java8Lexer.class.getConstructor(CharStream.class));
        }
        catch (NoSuchMethodException e) {
            addError(e);
        }
        try {
            bind(TokenStream.class).toConstructor(CommonTokenStream.class.getConstructor(TokenSource.class));
        }
        catch (NoSuchMethodException e) {
            addError(e);
        }
        try {
            bind(Parser.class).toConstructor(Java8Parser.class.getConstructor(TokenStream.class));
        }
        catch (NoSuchMethodException e) {
            addError(e);
        }
        ThrowingProviderBinder.create(binder())
                              .bind(IOProvider.class, CharStream.class)
                              .to(CharStreamProvider.class);
        bind(EventBus.class).to(EventBus.class).in(Singleton.class);
    }

}
