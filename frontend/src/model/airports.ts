import {Locations} from "./locations";
import {Page} from "./page";


export class Airports {
    locations: Locations[];
    page: Page;


    get Locations(): Locations[] {
        return this.locations;
    }

    get Page(): Page {
        return this.page;
    }
}
