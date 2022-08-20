package app.repository;

import app.DTO.PasseFilterDTO;
import app.entity.Danse;
import app.entity.Passe;
import app.entity.TypePasse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TypePasseRepositoryCustomImpl implements TypePasseRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DanseRepository danseRepository;

    @Override
    public List<TypePasse> findAllByCodeDanseOrderByCodeTypePasseAsc(String codeDanse) {
        List<TypePasse> resultat = new ArrayList<>();

        Integer idDanse = null;
        Optional<Danse> danse = danseRepository.findByCodedanse(codeDanse);
        if(danse.isPresent()) {
            idDanse = danse.get().getIddanse();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<TypePasse> cq = cb.createQuery(TypePasse.class);
            Root<TypePasse> root_typepasse = cq.from(TypePasse.class);

            // filtrage
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root_typepasse.get("danse").get("iddanse").in(idDanse));
            cq.where(predicates.toArray(new Predicate[0]));

            // sorting
            TypedQuery<TypePasse> typedQuery = entityManager.createQuery(cq);
            //cq.orderBy(QueryUtils.toOrders(pageable.getSort(), root_typepasse, cb));

            // recuperation resultat
            resultat.addAll(typedQuery.getResultList());
        }
        return resultat;
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
            if(entry.getValue().getMatchMode().equals("equals")) {
                if (entry.getKey().equals("danse.iddanse")) {
                    String value = entry.getValue().getValue().split(",")[0].split("=")[1];
                    predicates.add(root_passe.get("danse").get("iddanse").in(value));
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
