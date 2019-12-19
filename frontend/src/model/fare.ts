export class Fare {
    originCode: string;
    originName: string;
    originDescription: string;
    destinationCode: string;
    destinationName: string;
    destinationDescription: string;
    rate: number;
    currency: string;


    get OriginCode(): string {
        return this.originCode;
    }

    set OriginCode(value: string) {
        this.originCode = value;
    }

    get OriginName(): string {
        return this.originName;
    }

    set OriginName(value: string) {
        this.originName = value;
    }

    get OriginDescription(): string {
        return this.originDescription;
    }

    set OriginDescription(value: string) {
        this.originDescription = value;
    }

    get DestinationCode(): string {
        return this.destinationCode;
    }

    set DestinationCode(value: string) {
        this.destinationCode = value;
    }

    get DestinationName(): string {
        return this.destinationName;
    }

    set DestinationName(value: string) {
        this.destinationName = value;
    }

    get DestinationDescription(): string {
        return this.destinationDescription;
    }

    set DestinationDescription(value: string) {
        this.destinationDescription = value;
    }

    get Rate(): number {
        return this.rate;
    }

    set Rate(value: number) {
        this.rate = value;
    }


    get Currency(): string {
        return this.currency;
    }

    set Currency(value: string) {
        this.currency = value;
    }
}
