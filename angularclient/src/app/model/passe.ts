import { Niveau } from '../model/niveau';
import { TypePasse } from '../model/typepasse';

export class Passe {
    id: number;
    niveau: Niveau;
    typepasse: TypePasse;
    nom: string;
    cavalier: string;
    cavaliere: string;
    video: string;
}
