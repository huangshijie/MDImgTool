package org.huang.mdimgtool.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.huang.mdimgtool.provider.RepositoryProvider;
import org.huang.mdimgtool.provider.RepositoryProviderExistingClientImpl;
import org.huang.mdimgtool.util.Utils;

public class GitService {
	
	private static RepositoryProvider repoProvider = new RepositoryProviderExistingClientImpl(Utils.GIT_LOCAL_LOCATION + ".git");
    
    public static void gitPush(BufferedImage finalSelectedImgBuffer, String fileName) {
    	try (Repository repo = repoProvider.get();
                Git git = new Git(repo)) {
	    		createImgFromGitRoot(finalSelectedImgBuffer, fileName);
	    		git.add()
	    			.addFilepattern(fileName + Utils.FILE_SPLIT + Utils.IMG_FORMAT)
	    			.call();
	    		git.commit()
	    			.setMessage(fileName)
	    			.call();
	    		PushCommand pushCommand = git.push();
	    		pushCommand.setRemote("origin").add("master").setCredentialsProvider(new UsernamePasswordCredentialsProvider(Utils.USER_NAME, Utils.USER_PASSWORD));
	    		pushCommand.call();
    		} catch(FileNotFoundException e) {
        	   e.printStackTrace();
           } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    private static void createImgFromGitRoot(BufferedImage finalSelectedImgBuffer, String fileName) {
    	try {
			ImageIO.write(finalSelectedImgBuffer, Utils.IMG_FORMAT, new File(Utils.GIT_LOCAL_LOCATION + fileName + Utils.FILE_SPLIT + Utils.IMG_FORMAT));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
//    private static void createFileFromGitRoot(Repository repo, String filename, String content) throws FileNotFoundException {
//    	
//        File hello3 = new File(repo.getDirectory().getParent(), filename);
//        
//        try (PrintWriter out = new PrintWriter(hello3)) {
//            out.println(content);
//        }
//    }
    
}
