package org.huang.mdimgtool.provider;

import org.eclipse.jgit.lib.Repository;

public interface RepositoryProvider {
	
	Repository get() throws Exception;
	
}
