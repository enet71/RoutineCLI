import { createReducer } from '@ngrx/store';
import { initial#{{store.name.cap}}#State } from '../consts';

export const #{{store.name}}#Reducer = createReducer(
    initial#{{store.name.cap}}#State,
);
