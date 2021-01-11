import { Category } from './category';

export class Phrase {
    constructor(
        public id: number,
        public phrase: string,
        public description: string,
        public longdescription: string,
        public category: Category
    ){}
}