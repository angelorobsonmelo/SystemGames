package br.com.systemGames.jogo.resource.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.systemGames.excecao.BOException;
import br.com.systemGames.jogo.bo.impl.JogoBO;
import br.com.systemGames.jogo.model.JogoVO;
import br.com.systemGames.jogo.resource.IJogoResource;
import br.com.systemGames.util.VericadorDeJogoReliazadoTarefa;

@Path("jogo")
public class JogoResource implements IJogoResource {

	private JogoVO jogoVO;
	private JogoBO jogoBO;

	public JogoResource() {
		jogoVO = new JogoVO();
		jogoBO = new JogoBO();
	}


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("salvar")
	public String salvar(JogoVO jogoVO) throws SQLException, BOException {
		try {

			return jogoBO.salvar(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}


	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("remover/{sequencial}")
	public String remover(@PathParam("sequencial") Integer sequencial) throws SQLException, BOException {
		try {

			jogoVO.setSequencial(sequencial);
			return jogoBO.remover(jogoVO);


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarTodos")
	public ArrayList<JogoVO> listarTodos() throws SQLException, BOException {
		try {

			return jogoBO.listarTodos();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("retornarUltimoSequencial")
	public JogoVO retornarUltimoSequencial() throws SQLException, BOException {
		try {

			return jogoBO.retornarUltimoSequencial();


		} catch (Exception ex) {
			throw new BOException(ex);
		}finally {

			jogoBO = null;


		}
	}


	@GET
	@Path("VericadorDeJogoReliazadoTarefa")
	public String verficar(){


		try {

			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(VericadorDeJogoReliazadoTarefa.class)
					.withIdentity("verificarDeJogoRealizado")
					.build();

			// specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(  
							SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(5)
							.repeatForever())
							.build();  

			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
			sch.start();	    	
			sch.scheduleJob(job, trigger);		

		} catch (SchedulerException e) {
			e.printStackTrace();
		}


		return "OK";

	}


	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("");

	}


	@Override
	public ArrayList<JogoVO> listarPorParams(Integer sequencial)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}


	


	



}
