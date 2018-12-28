package com.solarest.mocoplus.config.common;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GitUtil {

    public static String LOCAL_REPO_PATH = "D:/repo";

    public static String LOCAL_REPO_GIT_CONFIG = "D:/repo/.git";

    public static String REMOTE_REPO_URI = "git@gitlab.com:wilson/test.git";

    public static String LOCAL_CODE_DIR = "D:/platplat";

    /**
     * 新建一个分支并同步到远程仓库
     *
     * @param branchName
     * @throws IOException
     * @throws GitAPIException
     */
    public static String newBranch(String branchName) throws IOException {
        String newBranchIndex = "refs/heads/" + branchName;
        String gitPathURI = "";
        Git git = Git.open(new File(LOCAL_REPO_GIT_CONFIG));
        try {

            //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支
            List<Ref> refs = git.branchList().call();
            for (Ref ref : refs) {
                if (ref.getName().equals(newBranchIndex)) {
                    System.out.println("Removing branch before");
                    git.branchDelete().setBranchNames(branchName).setForce(true)
                            .call();
                    break;
                }
            }
            //新建分支
            Ref ref = git.branchCreate().setName(branchName).call();
            //推送到远程
            git.push().add(ref).call();
            gitPathURI = REMOTE_REPO_URI + " " + "feature/" + branchName;
        } catch (GitAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return gitPathURI;
    }

    public static void commitFiles() throws IOException, GitAPIException {
        String filePath = "";
        Git git = Git.open(new File(LOCAL_REPO_GIT_CONFIG));
        //创建用户文件的过程
        File myfile = new File(filePath);
        myfile.createNewFile();
        git.add().addFilepattern("pets").call();
        //提交
        git.commit().setMessage("Added pets").call();
        //推送到远程
        git.push().call();
    }

    public static boolean pullBranchToLocal(String cloneURL) {
        boolean resultFlag = false;
        String[] splitURL = cloneURL.split(" ");
        String branchName = splitURL[1];
        String fileDir = LOCAL_CODE_DIR + "/" + branchName;
        //检查目标文件夹是否存在
        File file = new File(fileDir);
        if (file.exists()) {
            deleteFolder(file);
        }
        try {
            Git.cloneRepository().setURI(cloneURL).setDirectory(file).call();
            resultFlag = true;
        } catch (GitAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultFlag;
    }

    public static void deleteFolder(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
                files[i].delete();
            }
        }
    }

    public static void setupRepo() throws GitAPIException {
        //建立与远程仓库的联系，仅需要执行一次
        Git git = Git.cloneRepository().setURI(REMOTE_REPO_URI).setDirectory(new File(LOCAL_REPO_PATH)).call();
    }

}