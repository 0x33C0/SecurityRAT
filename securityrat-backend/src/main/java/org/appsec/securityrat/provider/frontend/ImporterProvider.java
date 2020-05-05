package org.appsec.securityrat.provider.frontend;

import java.util.Set;
import org.appsec.securityrat.web.dto.importer.FrontendObjectDto;
import org.appsec.securityrat.web.dto.importer.FrontendTypeDto;

public interface ImporterProvider {
    /**
     * Returns the structure of all types that are available for the import
     * assistant.
     * 
     * @return Either a collection of all types that are available for the
     *         import assistant or <code>null</code>, if an error occurred.
     */
    Set<FrontendTypeDto> getAvailableTypes();
    
    /**
     * Adjusts and stores the specified <code>objects</code> in the persistent
     * storage.
     * 
     * @param objects A collection of all objects that will be stored in the
     *                persistent storage.
     * 
     * @return Either <code>true</code>, if storing the objects succeeded,
     *         otherwise <code>false</code>.
     */
    boolean applyObjects(Set<FrontendObjectDto> objects);
}
