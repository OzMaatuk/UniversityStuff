/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproj;

import java.io.File;

/**
 *
 * @author Oz
 */
public class fileThread implements Runnable
{
    private cacheObj obj;
    private File f;
    
    public fileThread(cacheObj newObj, File newf)
    {
        obj = newObj;
        f = newf;
    }

    @Override
    public void run()
    {
        // by random access, go to the obj.getReq line
        // if an anware exist, fill it in the obj, update obj, update file
        // if not, write a new one
        // after all, check sync woth cache
    }
}
