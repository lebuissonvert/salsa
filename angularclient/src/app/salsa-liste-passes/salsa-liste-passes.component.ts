import { Component, OnInit, ViewChild } from '@angular/core';
import { Passe } from '../model/passe';
import { TypePasse } from '../model/typepasse';
import { Niveau } from '../model/niveau';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PasseService } from '../service/passe.service';
import { TypePasseService } from '../service/typepasse.service';
import { NiveauService } from '../service/niveau.service';
import { LazyLoadEvent, SortEvent, FilterMetadata } from 'primeng/api';
import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'app-salsa-liste-passes',
  templateUrl: './salsa-liste-passes.component.html',
  styleUrls: ['./salsa-liste-passes.component.css']
})
export class SalsaListePassesComponent implements OnInit {
  // Objets + total retournés par la recherche
  passes: Passe[];
  totalRecords: number;
  // Définition + mapping model des colonnes du tableau
  cols: any[];
  // affichage true/false de l'icone tournante de chargement
  loading: boolean = false;
  // objet utilisé comme model de la dialog
  modalPasse: Passe = new Passe();
  displayDialogEdition: boolean = false;
  isModeConsultation: boolean = false;
  editionButtonCount: number = 0;
  isEditionButtonHidden: boolean = true;
  niveauFilter: SelectItem[];
  niveauxPasses: Niveau[];
  typePasseFilter: SelectItem[];
  typesPasses: TypePasse[];
  lastLazyLoadEvent: LazyLoadEvent;
  titreDialog: string;
  selectedNiveau: Niveau;

  constructor(private passeService: PasseService, private niveauService: NiveauService,
              private typePasseService: TypePasseService) {
  }

  ngOnInit() {
      this.cols = [
                  { field: 'niveau.codeniveau', header: 'Niveau' },
                  { field: 'typepasse.codetypepasse', header: 'Type' },
                  { field: 'nom', header: 'Nom' },
                  { field: 'cavalier', header: 'Notes cavalier' },
                  { field: 'cavaliere', header: 'Notes cavalière' }
              ];
      this.getNiveauFilter();
      this.getTypePasseFilter();
  }

  getTypePasseFilter() {
    this.loading = true;
    this.typePasseService.findAll().subscribe(data => {
      this.typePasseFilter = [];
      this.typesPasses = [];
      data.forEach(el => {
        this.typePasseFilter.push({label: el.codetypepasse, value: el.idtypepasse});
      });
      this.typesPasses = data;
      this.loading = false;
    });
  }

  getNiveauFilter() {
    this.loading = true;
    this.niveauService.findAll().subscribe(data => {
      this.niveauFilter = [];
      this.niveauxPasses = [];
      data.forEach(el => {
        this.niveauFilter.push({label: el.codeniveau, value: el.idniveau});
      });
      this.niveauxPasses = data;
      this.loading = false;
    });
  }

  loadDataLazy(event: LazyLoadEvent) {
    this.loading = true;
    this.lastLazyLoadEvent = event;
    this.passeService.findAllPaginatedFiltered(
              event.first / event.rows, event.rows,
              event.sortField, event.sortOrder, event.filters).subscribe(data => {
      this.passes = data.passes;
      this.totalRecords = data.totalRecords;
      this.loading = false;
    });
  }

  onRowSelect(event) {
    //this.modalPasse = event.data;
    this.showDialog("Consultation / modification de la passe", true);
    this.passeService.readPasse(this.modalPasse.id).subscribe();
  }

  enregistrerPasse() {
    this.passeService.savePasse(this.modalPasse).subscribe(result => this.hideDialog());
  }

  ajouterPasse() {
    this.showDialog("Ajout d'une passe", false);
  }

  showModeModification() {
    this.isModeConsultation = false;
  }

  unlockEditionButton() {
    this.editionButtonCount++;
    if(this.editionButtonCount > 20) {
      this.isEditionButtonHidden = false;
    }
  }

  showDialog(titre: string, isModeConsultation: boolean) {
    this.titreDialog = titre;
    this.displayDialogEdition = true;
    this.isModeConsultation = isModeConsultation;
  }

  hideDialog() {
    this.displayDialogEdition = false;
    this.modalPasse = new Passe();
    this.loadDataLazy(this.lastLazyLoadEvent);
  }
}
