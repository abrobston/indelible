package com.brobston.indelible.processor.pre;

import java.util.Collection;

/**
 * Created by andrew on 11/2/14.
 */
public interface CodeMetadata {
    /**
     * Gets a String to indicate the version of the metadata format.  This value
     * is needed in the event that stale metadata files are cached and need to
     * be refreshed.  The version is updated only when there is some change to the
     * format (e.g., when the file format of metadata files is changed).
     *
     * @return A String indicating the version of the metadata format.
     */
    String getMetadataVersion();

    /**
     * Gets the fully-qualified name of the class to which this metadata applies.
     *
     * @return The fully-qualified name of the class to which this metadata applies.
     */
    String getClassName();

    /**
     * Gets a value indicating whether this metadata is read-only.  For safety,
     * concrete instances of {@code CodeMetadata} should be read-only during
     * post-processing.
     *
     * @return A Boolean indicating whether this metadata is read-only.
     */
    Boolean isReadOnly();

    /**
     * Indicates whether, at the current point in processing, the class that this
     * metadata describes is known to be generic.  That is, does the class itself
     * have generic type parameters?
     *
     * @return A Boolean indicating whether the described class is generic.
     */
    Boolean isClassGeneric();

    /**
     * Indicates whether, at the current point in processing, the class that this
     * metadata describes defines any generic methods.  That is, are there any
     * methods with generic type parameters?  Inherited methods are not included
     * in this analysis unless they are overridden in the described class.
     *
     * @return A Boolean indicating whether the described class defines any generic
     * methods of its own.
     */
    Boolean hasGenericMethods();

    /**
     * Indicates whether, at the current point in processing, the class that this
     * metadata describes calls any generic methods.
     *
     * @return A Boolean indicating whether the described class calls any generic
     * methods.
     */
    Boolean callsGenericMethods();

    /**
     * Gets a read-only collection of {@code CodeMetadataInfo} objects pertaining to
     * the described class.  Adding and removing objects from this collection should
     * be done using the {@code addCodeMetadataInfo} and {@code removeCodeMetadataInfo}
     * methods.
     *
     * @return A read-only collection of {@code CodeMetadataInfo} objects pertaining to
     * the described class.
     */
    Collection<CodeMetadataInfo> getCodeMetadataInfoCollection();

    /**
     * If this metadata is not read-only, adds a new {@code CodeMetadataInfo} object that
     * pertains to the described class.
     *
     * @param codeMetadataInfo A {@code CodeMetadataInfo} object pertaining to the described class.
     */
    void addCodeMetadataInfo(CodeMetadataInfo codeMetadataInfo);

    /**
     * If this metadata is not read-only, attempts to remove the specified
     * {@code CodeMetadataInfo} object and indicates whether the object was
     * actually removed.
     *
     * @param codeMetadataInfo The {@code CodeMetadataInfo} object to attempt to remove.
     * @return A Boolean indicating whether the object was actually removed.
     */
    Boolean removeCodeMetadataInfo(CodeMetadataInfo codeMetadataInfo);
}
