import { Pipe, PipeTransform } from '@angular/core';
import { Sweet } from '../Models/sweet';

@Pipe({
  name: 'sweetfilter'
})

//class for filtering the items by name
export class SweetfilterPipe implements PipeTransform {
  transform(sweets: Sweet[], searchSweet: string): Sweet[] {
    if (!sweets || !searchSweet){
      return sweets;
    }

    return sweets.filter(sweet =>
       sweet.sweetItemName.toLowerCase().indexOf(searchSweet.toLowerCase()) !== -1);
  }

}
