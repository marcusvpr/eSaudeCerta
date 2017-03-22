package com.mpxds.mpbasic.repository.iff;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mpxds.mpbasic.model.iff.MpUsuarioIff;
import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.repository.filter.iff.MpUsuarioIffFilter;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpUsuarioIffs implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// Rotina de Filtro da tela Pesquisa...
	//
	private Criteria criarCriteriaParaFiltro(MpUsuarioIffFilter filtro) {
		//
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(MpUsuarioIff.class);
		
		if (StringUtils.isNotBlank(filtro.getNome()))
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		if (StringUtils.isNotBlank(filtro.getEmail()))
			criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));

		if (filtro.getMpSetorIffs() != null && filtro.getMpSetorIffs().length > 0) {
			criteria.add(Restrictions.in("mpSetorIff", filtro.getMpSetorIffs()));
		}

		if (StringUtils.isNotBlank(filtro.getRamal()))
			criteria.add(Restrictions.ilike("ramal", filtro.getRamal(), MatchMode.ANYWHERE));
		//
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	public List<MpUsuarioIff> filtrados(MpUsuarioIffFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getMpFilterOrdenacao().getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getMpFilterOrdenacao().getQuantidadeRegistros());
		
		if (filtro.getMpFilterOrdenacao().isAscendente() && filtro.getMpFilterOrdenacao().
															getPropriedadeOrdenacao() != null)
			criteria.addOrder(Order.asc(filtro.getMpFilterOrdenacao().
																	getPropriedadeOrdenacao()));
		else if (filtro.getMpFilterOrdenacao().getPropriedadeOrdenacao() != null)
			criteria.addOrder(Order.desc(filtro.getMpFilterOrdenacao().
																	getPropriedadeOrdenacao()));
		//
		return criteria.list();
	}
	
	public int quantidadeFiltrados(MpUsuarioIffFilter filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	
	public MpUsuarioIff guardar(MpUsuarioIff mpUsuarioIffs) {
		try {
			return manager.merge(mpUsuarioIffs);
		} catch (OptimisticLockException e) {
			throw new MpNegocioException(
					"Erro de concorrência. Esse Usuário Iff... já foi alterado anteriormente!");
		}
	}
	
	@MpTransactional
	public void remover(MpUsuarioIff mpUsuarioIffs) throws MpNegocioException {
		try {
			mpUsuarioIffs = porId(mpUsuarioIffs.getId());
			manager.remove(mpUsuarioIffs);
			manager.flush();
		} catch (PersistenceException e) {
			throw new MpNegocioException("Usuário Iff... não pode ser excluído.");
		}
	}

	public MpUsuarioIff porEmail(String email) {
		try {
			return manager.createQuery("from MpUsuarioIff where upper(email) = :email",
																			MpUsuarioIff.class)
				.setParameter("email", email.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<MpUsuarioIff> byEmailList() {
		return this.manager.createQuery("from MpUsuarioIff ORDER BY email", MpUsuarioIff.class)
																			.getResultList();
	}

	public List<MpUsuarioIff> byNomeList() {
		return this.manager.createQuery("from MpUsuarioIff ORDER BY nome", MpUsuarioIff.class)
																			.getResultList();
	}

	public List<MpUsuarioIff> byRamalList() {
		return this.manager.createQuery("from MpUsuarioIff ORDER BY ramal, nome", 
															MpUsuarioIff.class).getResultList();
	}


	public int countByEmail(String email) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(MpUsuarioIff.class);
		
		criteria.add(Restrictions.eq("email", email));
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	public MpUsuarioIff porId(Long id) {
		return manager.find(MpUsuarioIff.class, id);
	}

	public MpUsuarioIff porNavegacao(String acao, String nome) {
		//
//		System.out.println("MpUsuarioIffss.MpUsuarioIffs ( " + acao + " / " + nome);
		//
		try {
			//
			if (acao.equals("mpEnd") || acao.equals("mpPrev")) 
				return manager.createQuery(
					"from MpUsuarioIff where upper(nome) < :nome" +
					" ORDER BY nome DESC", MpUsuarioIff.class)
					.setParameter("nome", nome.toUpperCase())
					.setMaxResults(1)
					.getSingleResult();
			else 
			if (acao.equals("mpFirst") || acao.equals("mpNext")) 
				return manager.createQuery(
					"from MpUsuarioIff where upper(nome) > :nome" +
					" ORDER BY nome ASC", MpUsuarioIff.class)
					.setParameter("nome", nome.toUpperCase())
					.setMaxResults(1)
					.getSingleResult();
			else
				return null;
			//
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public long quantidadeRegistros() {
		//
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(MpUsuarioIff.class);
		
		criteria.add(Restrictions.gt("id", 0L));
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).longValue();
		
//		return manager.createQuery("select count(c) from MpUsuarioIffs c",
//										Long.class).getSingleResult();
	}

}