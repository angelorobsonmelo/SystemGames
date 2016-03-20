package br.teste.jogo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		System.out.println("TestJob run successfully...");
	}

}
