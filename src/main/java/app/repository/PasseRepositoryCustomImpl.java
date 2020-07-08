package app.repository;

import app.DTO.PasseFilterDTO;
import app.entity.Passe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class PasseRepositoryCustomImpl implements PasseRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public long count(HashMap<String, PasseFilterDTO> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Passe> cq = cb.createQuery(Passe.class);
        Root<Passe> root_passe = cq.from(Passe.class);

        // filtrage
        List<Predicate> predicates = getFiltersPredidate(root_passe, cb, filters);
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Passe> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList().size();
    }

    @Override
    public List<Passe> findAllFiltered(Pageable pageable, HashMap<String, PasseFilterDTO> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Passe> cq = cb.createQuery(Passe.class);
        Root<Passe> user = cq.from(Passe.class);

        // filtrage
        List<Predicate> predicates = getFiltersPredidate(user, cb, filters);
        cq.where(predicates.toArray(new Predicate[0]));

        // sorting
        cq.orderBy(QueryUtils.toOrders(pageable.getSort(), user, cb));

        // paging
        TypedQuery<Passe> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((int) (pageable.getOffset()));
        typedQuery.setMaxResults(pageable.getPageSize());

        // recuperation resultat
        return typedQuery.getResultList();
    }

    private List<Predicate> getFiltersPredidate (
            Root<Passe> root_passe, CriteriaBuilder cb,
            HashMap<String, PasseFilterDTO> filters) {
        List<Predicate> predicates = new ArrayList<>();

        for(Map.Entry<String, PasseFilterDTO> entry : filters.entrySet()) {
            if(entry.getValue().getMatchMode().equals("contains")) {
                if(entry.getKey().equals("global")) {
                    Predicate idPredicate =
                            cb.like(root_passe.get("id").as(String.class), "%" + entry.getValue().getValue() + "%");
                    Predicate loginPredicate =
                            cb.like(root_passe.get("nom"), "%" + entry.getValue().getValue() + "%");
                    Predicate cavalierPredicate =
                            cb.like(root_passe.get("cavalier"), "%" + entry.getValue().getValue() + "%");
                    Predicate cavalierePredicate =
                            cb.like(root_passe.get("cavaliere"), "%" + entry.getValue().getValue() + "%");
                    Predicate videoPredicate =
                            cb.like(root_passe.get("video"), "%" + entry.getValue().getValue() + "%");
                    Predicate codeniveauPredicate =
                            cb.like(root_passe.get("niveau").get("codeniveau"), "%" + entry.getValue().getValue() + "%");
                    Predicate codetypepassePredicate =
                            cb.like(root_passe.get("typepasse").get("codetypepasse"), "%" + entry.getValue().getValue() + "%");
                    predicates.add(
                            cb.or(loginPredicate, idPredicate, videoPredicate, cavalierPredicate,
                                    cavalierePredicate, codeniveauPredicate, codetypepassePredicate)
                    );
                } else {
                    predicates.add(cb.like(root_passe.get(entry.getKey()), "%" + entry.getValue().getValue() + "%"));
                }
            }
            if(entry.getValue().getMatchMode().equals("in")) {
                if (entry.getKey().equals("niveau.codeniveau")) {
                    String value = entry.getValue().getValue();
                    List<String> values =
                            new ArrayList<String>(Arrays.asList(
                                    value.replace("[", "")
                                            .replace("]", "")
                                            .split(", ")));
                    // la valeur envoyée est celle de l'id : on applique donc un filtre sur idniveau
                    predicates.add(root_passe.get("niveau").get("idniveau").in(values));
                } else if (entry.getKey().equals("typepasse.codetypepasse")) {
                    String value = entry.getValue().getValue();
                    List<String> values =
                            new ArrayList<String>(Arrays.asList(
                                    value.replace("[", "")
                                            .replace("]", "")
                                            .split(", ")));
                    // la valeur envoyée est celle de l'id : on applique donc un filtre sur idtypepasse
                    predicates.add(root_passe.get("typepasse").get("idtypepasse").in(values));
                }
            }
        }
        return predicates;
    }
}
