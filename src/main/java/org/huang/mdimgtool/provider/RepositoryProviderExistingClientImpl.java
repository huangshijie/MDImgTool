package org.huang.mdimgtool.provider;

import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

public class RepositoryProviderExistingClientImpl implements RepositoryProvider{
	private String clientPath;
	
    public RepositoryProviderExistingClientImpl(String clientPath) {
        this.clientPath = clientPath;
    }
    
	@Override
	public Repository get() throws Exception {
		try (Repository repo = new FileRepository(clientPath)) {
            return repo;
        }
	}

}
